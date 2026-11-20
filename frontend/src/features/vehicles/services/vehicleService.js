const API_BASE_URL = "http://localhost:8081/api/v1";

export async function getVehicleMakes() {
    const response = await fetch(
        `${API_BASE_URL}/vehicle-makes`
    );

    if (!response.ok) {
        throw new Error(
            "Failed to fetch vehicle makes"
        );
    }

    return response.json();
}

export async function getVehicleModels(makeId) {
    const response = await fetch(
        `${API_BASE_URL}/vehicle-models?makeId=${makeId}`
    );

    if (!response.ok) {
        throw new Error(
            "Failed to fetch vehicle models"
        );
    }

    return response.json();
}

export async function getVehicleVariants(modelId) {
    const response = await fetch(
        `${API_BASE_URL}/vehicle-variants?modelId=${modelId}`
    );

    if (!response.ok) {
        throw new Error(
            "Failed to fetch vehicle variants"
        );
    }

    return response.json();
}