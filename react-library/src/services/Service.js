const URL = "http://localhost:8080/";

export async function postDocument(document) {
    return await fetch(URL + "documents",
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(document)
        });
}

export async function postClient(client) {
    return await fetch(URL + "clients",
        {
            method: "POST",
            headers: {
                "content-type": "application/json"
            },
            body: JSON.stringify(client)
        });
}

export function fetchAttendant(attendantId) {
    return fetch(URL + "attendants/" + attendantId);
}

export function fetchClient(clientId) {
    return fetch(URL + "clients/" + clientId);
}

export async function putBorrow(borrow) {
    return await fetch(URL + "borrows",
        {
            method: "PUT",
            headers:
            {
                "Content-Type":
                    "application/json"
            },
            body:
                JSON.stringify(borrow)
        });
}

export function fetchBorrows(userId) {
    return fetch(URL + "borrows/" + userId);
}

export async function postBorrow(documentId, clientId) {
    return await fetch(URL + "borrows",
        {
            method: "POST",
            headers:
            {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ "documentId": documentId, "clientId": clientId })
        }
    );
}

export async function putClient(client) {
    return await fetch(URL + "clients/" + client.id,
        {
            method: "PUT",
            headers:
            {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(client)
        }
    );
}

export function fetchDocuments() {
    return fetch(URL + "documents");
}

export async function fetchClients() {
    return await fetch(URL + "clients");
}

export async function fetchAttendants() {
    return await fetch(URL + "attendants");
}

export function fetchClientFines(clientId) {
    return fetch(URL + "fines/" + clientId)
}

export async function putFines(clientId) {
    return await fetch(URL + "fines/" + clientId,
        {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: clientId
        });
}

