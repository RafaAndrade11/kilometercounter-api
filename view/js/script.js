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

async function addClient(clientData) {
    try {
        const response = await fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(clientData)
        });
        if (!response.ok) {
            throw new Error("Erro ao adicionar cliente");
        }
        const newClient = await response.json();
        return newClient;
    } catch (error) {
        console.error("Erro ao adicionar cliente:", error);
        throw error;
    }
}

async function handleSubmit(event) {
    event.preventDefault();

    const formData = new FormData(event.target);
    const clientData = {
        name: formData.get("name"),
        cep: formData.get("cep")
    };

    try {
        const newClient = await addClient(clientData);
        const clients = await fetchClients();
        renderClients(clients);
        event.target.reset(); // Limpa o formulário após a submissão
    } catch (error) {
        console.error("Erro ao lidar com o envio do formulário:", error);
    }
}

async function init() {
    const clients = await fetchClients();
    renderClients(clients);

     const clientForm = document.getElementById("clientForm");
     clientForm.addEventListener("submit", handleSubmit);
}

init();