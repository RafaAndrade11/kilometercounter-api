const url = "http://localhost:8080/client";
const routeUrl = "http://localhost:8080/route";

async function fetchClients() {
    try {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error("Erro ao obter os clientes");
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error("Erro:", error);
        return [];
    }
}

function populateClientsSelect(clients) {
    const initialClientSelect = document.getElementById("originClientId");
    const finalClientSelect = document.getElementById("destinationClientId");

    clients.forEach(client => {
        const option = document.createElement("option");
        option.value = client.id;
        option.textContent = client.name;
        initialClientSelect.appendChild(option.cloneNode(true));
        finalClientSelect.appendChild(option);
    });
}

async function fetchRoutes() {
    try {
        const response = await fetch(routeUrl);
        if (!response.ok) {
            throw new Error("Erro ao obter as rotas");
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error("Erro:", error);
        return [];
    }
}

function renderRoutes(routes) {
    const routesContainer = document.getElementById("routes");
    routesContainer.innerHTML = ""; // Limpa o conteúdo anterior
    routes.forEach(route => {
        const routeRow = document.createElement("tr");
        routeRow.innerHTML = `
            <td>${route.id}</td>
            <td>${route.originClient.name}</td>
            <td>${route.destinationClient.name}</td>
            <td>${route.distance} km</td>
        `;
        routesContainer.appendChild(routeRow);
    });
}

async function init() {
    const clients = await fetchClients();
    populateClientsSelect(clients);
    const routes = await fetchRoutes();
    renderRoutes(routes);
}

document.getElementById("routeForm").addEventListener("submit", async function(event) {
    event.preventDefault(); // Evita o comportamento padrão de envio do formulário

    const formData = new FormData(this);
    const routeData = {
        originClientId: formData.get("originClientId"),
        destinationClientId: formData.get("destinationClientId")
    };

    try {
        const response = await fetch(routeUrl, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(routeData)
        });

        if (!response.ok) {
            throw new Error("Erro ao cadastrar a rota");
        }

        // Atualiza a lista de rotas após o cadastro bem-sucedido
        const routes = await fetchRoutes();
        renderRoutes(routes);

        // Limpa os campos do formulário após o cadastro
        this.reset();
    } catch (error) {
        console.error("Erro:", error);
    }
});

init();

