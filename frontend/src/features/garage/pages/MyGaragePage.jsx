function MyGaragePage() {
    return (
        <div
            style={{
                padding: "20px",
                maxWidth: "800px",
                margin: "0 auto"
            }}
        >
            <h1>My Garage</h1>

            <p>
                Save your vehicles and
                quickly find compatible
                spare parts.
            </p>

            <div
                style={{
                    padding: "20px",
                    backgroundColor: "#f8f9fa",
                    borderRadius: "8px",
                    border: "1px solid #ddd",
                    marginTop: "20px"
                }}
            >
                <h3>
                    Coming Soon
                </h3>

                <p>
                    Garage functionality will
                    be available after
                    authentication is implemented
                    in Day 4.
                </p>

                <p>
                    Future features:
                </p>

                <ul>
                    <li>
                        Save multiple vehicles
                    </li>

                    <li>
                        Set default vehicle
                    </li>

                    <li>
                        View compatible parts
                    </li>

                    <li>
                        Vehicle-based search
                    </li>
                </ul>
            </div>
        </div>
    );
}

export default MyGaragePage;