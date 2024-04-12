const url = "http://localhost:8080/client";

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

function renderClients(clients) {
    const clientsContainer = document.getElementById("clients");
    clientsContainer.innerHTML = ""; // Limpa o conteúdo anterior
    clients.forEach(client => {
        const clientRow = document.createElement("tr");
        clientRow.innerHTML = `
            <td>${client.id}</td>
            <td>${client.name}</td>
            <td>${client.cep}</td>
        `;
        clientsContainer.appendChild(clientRow);
    });
}

async function init() {
    const clients = await fetchClients();
    renderClients(clients);
}

init();