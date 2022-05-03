const URL = "http://localhost:8080/";

export async function postBook(book) {
    return await fetch(URL + "documents",
        {
            method: "POST",
            headers: {
                "content-type": "application/json"
            },
            body: JSON.stringify(book)
        });
}

export async function postCD(CD) {
    return await fetch(URL + "http://localhost:8080/documents",
        {
            method: "POST",
            headers: {
                "content-type": "application/json"
            },
            body: JSON.stringify(CD)
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

export async function postDVD(DVD) {
    return await fetch(URL + "documents",
        {
            method: "POST",
            headers: {
                "content-type": "application/json"
            },
            body: JSON.stringify(DVD)
        });
}

export async function fetchAttendant(attendantId) {
    const response = await fetch(URL + "attendants/" + attendantId);
    return await response.json();
}

export async function fetchClient(clientId) {
    const response = await fetch(URL + "clients/" + clientId);
    return await response.json();
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

export async function fetchBorrows(userId) {
    const response = await fetch(URL + "borrows/" + userId);
    return await response.json();
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

export async function fetchDocuments() {
    const response = await fetch(URL + "documents");
    return await response.json();
}

export async function fetchClients() {
    const response = await fetch(URL + "clients");
    return await response.json();
}

export async function fetchAttendants() {
    const response = await fetch(URL + "attendants");
    return await response.json();
}

