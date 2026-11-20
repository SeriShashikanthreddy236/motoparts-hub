import VehicleSelector from "../components/VehicleSelector";

function VehicleCatalogPage() {
    return (
        <div
            style={{
                padding: "20px",
                maxWidth: "600px",
                margin: "0 auto",
            }}
        >
            <h1>Vehicle Catalog</h1>

            <p>
                Select your vehicle to find
                compatible spare parts.
            </p>

            <VehicleSelector />
        </div>
    );
}

export default VehicleCatalogPage;