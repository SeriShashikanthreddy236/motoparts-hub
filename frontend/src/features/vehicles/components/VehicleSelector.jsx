import { useEffect, useState } from "react";

import MakeDropdown from "./MakeDropdown";
import ModelDropdown from "./ModelDropdown";
import VariantDropdown from "./VariantDropdown";

import {
    getVehicleMakes,
    getVehicleModels,
    getVehicleVariants,
} from "../services/vehicleService";

function VehicleSelector() {
    const [makes, setMakes] = useState([]);
    const [models, setModels] = useState([]);
    const [variants, setVariants] = useState([]);

    const [selectedMake, setSelectedMake] = useState("");
    const [selectedModel, setSelectedModel] = useState("");
    const [selectedVariant, setSelectedVariant] = useState("");

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    useEffect(() => {
        loadMakes();
    }, []);

    async function loadMakes() {
        try {
            setLoading(true);
            setError("");

            const response = await getVehicleMakes();

            setMakes(response.data || []);
        } catch (error) {
            setError(
                "Failed to load vehicle manufacturers"
            );
        } finally {
            setLoading(false);
        }
    }

    async function handleMakeChange(event) {
        const makeId = event.target.value;

        setSelectedMake(makeId);

        // Reset dependent selections
        setSelectedModel("");
        setSelectedVariant("");

        setModels([]);
        setVariants([]);

        if (!makeId) return;

        try {
            setLoading(true);
            setError("");

            const response =
                await getVehicleModels(makeId);

            setModels(response.data || []);
        } catch (error) {
            setError(
                "Failed to load vehicle models"
            );
        } finally {
            setLoading(false);
        }
    }

    async function handleModelChange(event) {
        const modelId = event.target.value;

        setSelectedModel(modelId);

        // Reset variant
        setSelectedVariant("");
        setVariants([]);

        if (!modelId) return;

        try {
            setLoading(true);
            setError("");

            const response =
                await getVehicleVariants(modelId);

            setVariants(response.data || []);
        } catch (error) {
            setError(
                "Failed to load vehicle variants"
            );
        } finally {
            setLoading(false);
        }
    }

    function handleVariantChange(event) {
        setSelectedVariant(event.target.value);
    }

    const selectedMakeObject =
        makes.find(
            (make) =>
                String(make.id) ===
                String(selectedMake)
        );

    const selectedModelObject =
        models.find(
            (model) =>
                String(model.id) ===
                String(selectedModel)
        );

    const selectedVariantObject =
        variants.find(
            (variant) =>
                String(variant.id) ===
                String(selectedVariant)
        );

    return (
        <div>
            <h2>Vehicle Selector</h2>

            {loading && (
                <p>
                    Loading vehicle data...
                </p>
            )}

            {error && (
                <div
                    style={{
                        color: "red",
                        backgroundColor: "#ffe6e6",
                        padding: "10px",
                        marginBottom: "15px",
                        borderRadius: "5px",
                    }}
                >
                    {error}
                </div>
            )}

            <MakeDropdown
                makes={makes}
                value={selectedMake}
                onChange={handleMakeChange}
            />

            <br />
            <br />

            <ModelDropdown
                models={models}
                value={selectedModel}
                onChange={handleModelChange}
                disabled={!selectedMake}
            />

            {selectedMake &&
                !loading &&
                models.length === 0 && (
                    <p>
                        No models available
                    </p>
                )}

            <br />
            <br />

            <VariantDropdown
                variants={variants}
                value={selectedVariant}
                onChange={handleVariantChange}
                disabled={!selectedModel}
            />

            {selectedModel &&
                !loading &&
                variants.length === 0 && (
                    <p>
                        No variants available
                    </p>
                )}

            <br />
            <br />

            {selectedVariant && (
                <div
                    style={{
                        border: "1px solid #ddd",
                        padding: "15px",
                        borderRadius: "8px",
                        backgroundColor:
                            "#f8f9fa",
                        marginTop: "20px",
                    }}
                >
                    <h3>
                        Selected Vehicle
                    </h3>

                    <p>
                        <strong>Make:</strong>{" "}
                        {
                            selectedMakeObject?.name
                        }
                    </p>

                    <p>
                        <strong>Model:</strong>{" "}
                        {
                            selectedModelObject?.name
                        }
                    </p>

                    <p>
                        <strong>Variant:</strong>{" "}
                        {
                            selectedVariantObject?.name
                        }
                    </p>

                    <hr />

                    <p>
                        Ready to search
                        compatible spare parts
                        for this vehicle.
                    </p>
                </div>
            )}
        </div>
    );
}

export default VehicleSelector;