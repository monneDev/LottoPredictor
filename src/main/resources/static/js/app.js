// mock data
const MOCK_DATA = {
    regularLotto: Array.from({ length: 10 }, () => ({ numbers: uniq(7, 36), stars: [] })),
    viking:       Array.from({ length: 10 }, () => ({ numbers: uniq(6, 48), stars: uniq(1, 5) })),
    euro:         Array.from({ length: 10 }, () => ({ numbers: uniq(5, 50), stars: uniq(2, 12) }))
};

function uniq(count, max) {
    const s = new Set();
    while (s.size < count) s.add(1 + Math.floor(Math.random() * max));
    return [...s].sort((a,b) => a - b);
}

// vælg hvilken “service” der er aktiv nu
const getCombos = getCombosMock; // byt til getCombosFromApi når backend er klar

function getCombosMock(lottoKey) {
    return Promise.resolve(MOCK_DATA[lottoKey] ?? []);
}

// (bruger du senere backend:)
async function getCombosFromApi(lottoKey) {
    const urlMap = {
        regularLotto: '/api/regular-lotto/top10',
        viking: '/api/viking-lotto/top10',
        euro: '/api/euro-lotto/top10'
    };
    const res = await fetch(urlMap[lottoKey], { headers: { 'Accept': 'application/json' } });
    if (!res.ok) throw new Error('Server error');
    return res.json();
}

// ---- DOM & UI ----
document.addEventListener('DOMContentLoaded', () => {
    const selectionCards = document.querySelectorAll(".lotto-card"); // FIX: All
    const resultsSection = document.getElementById("results");
    const emptyState     = document.getElementById("emptyState");
    const combosGrid     = document.getElementById("combos");
    const regenBtn       = document.getElementById("regenBtn");
    const loadingEl      = document.getElementById("loading");

    let selectedLotto = null;

    function setSelected(lottoKey) {
        selectedLotto = lottoKey;
        combosGrid.dataset.selectedLotto = lottoKey;

        selectionCards.forEach(card => {
            const isSel = card.dataset.lotto === lottoKey;
            card.classList.toggle('is-selected', isSel);
            card.setAttribute('aria-pressed', String(isSel));
        });

        emptyState.classList.add('hidden');
        resultsSection.classList.remove('hidden');
    }

    function showLoading(isLoading) {
        resultsSection.setAttribute('aria-busy', String(isLoading));
        loadingEl.classList.toggle('hidden', !isLoading);
    }

    function renderCombos(combos, lottoKey) {
        combosGrid.innerHTML = '';
        combos.forEach((c, i) => {
            const card = document.createElement('article');
            card.className = 'combo-card';
            card.style.animationDelay = `${i * 50}ms`;

            const head = document.createElement('div');
            head.className = 'combo-head';
            head.innerHTML = `<h3 class="combo-title">Combination #${i + 1}</h3>`;

            const balls = document.createElement('div');
            balls.className = 'combo-balls';

            c.numbers.forEach(n => {
                const el = document.createElement('div');
                el.className = `ball ${
                    lottoKey === 'viking' ? 'viking-ball' :
                        lottoKey === 'euro'   ? 'euro-ball'   :
                            'regularLotto-ball'
                }`;
                el.textContent = n;
                balls.appendChild(el);
            });

            if (Array.isArray(c.stars) && c.stars.length) {
                c.stars.forEach(s => {
                    const el = document.createElement('div');
                    if (lottoKey === 'euro') {
                        el.className = 'ball star-ball';            // Eurojackpot stjerne
                    } else if (lottoKey === 'viking') {
                        el.className = 'ball vikingNumber-ball';    // Viking Lotto ekstra-tal
                    } else {
                        el.className = 'ball regularLotto-ball';    // fallback (hvis Regular skulle have noget)
                    }
                    el.textContent = s;
                    balls.appendChild(el);
                });
            }

            card.appendChild(head);
            card.appendChild(balls);
            combosGrid.appendChild(card);
        });
    }

    async function generateAndRender() {
        if (!selectedLotto) return;
        try {
            showLoading(true);
            const combos = await getCombos(selectedLotto); // FIX: getCombos defineret
            renderCombos(combos, selectedLotto);
        } catch (e) {
            console.error(e);
            combosGrid.innerHTML = '<p style="color:#b91c1c;">Kunne ikke hente kombinationer.</p>';
        } finally {
            showLoading(false);
        }
    }

    // events
    selectionCards.forEach(card => {
        const key = card.dataset.lotto; // regularLotto | viking | euro
        card.addEventListener('click', async () => {
            setSelected(key);
            await generateAndRender();
        });
        card.addEventListener('keydown', e => {
            if (e.key === 'Enter' || e.key === ' ') { e.preventDefault(); card.click(); }
        });
    });

    regenBtn?.addEventListener('click', generateAndRender);
});
