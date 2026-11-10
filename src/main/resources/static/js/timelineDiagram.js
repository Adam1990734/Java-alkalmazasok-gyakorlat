(async () => {
    const timeline = await fetch("/api/diagrams/timeline").then(data => data.json());
    const currentYear = new Date().getFullYear();
    new Chart(
        document.getElementById('timelineDiagram'),
        {
            type: 'bar',
            data: {
                labels: timeline.map(x => x.name),
                datasets: [{
                    label: 'Ages',
                    data: timeline.map(x => [
                        Number(x.bornat),
                        Number(x.diedat) === 0 ? currentYear : Number(x.diedat)
                    ]),
                    backgroundColor: '#4BC0C0'
                }]
            },
            options: {
                indexAxis: 'y',
                scales: {
                    x: {
                        min: 1700,
                        max: currentYear,
                        title: {
                            display: true,
                            text: 'Age'
                        }
                    }
                }
            }
        }
    );
})();