function ModelDropdown({
    models,
    value,
    onChange,
    disabled
}) {

    return (
        <select
            value={value}
            onChange={onChange}
            disabled={disabled}
        >
            <option value="">
                Select Model
            </option>

            {models.map((model) => (
                <option
                    key={model.id}
                    value={model.id}
                >
                    {model.name}
                </option>
            ))}
        </select>
    );
}

export default ModelDropdown;