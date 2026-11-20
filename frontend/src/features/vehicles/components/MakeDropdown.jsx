function MakeDropdown({
    makes,
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
                Select Manufacturer
            </option>

            {makes.map((make) => (
                <option
                    key={make.id}
                    value={make.id}
                >
                    {make.name}
                </option>
            ))}
        </select>
    );
}

export default MakeDropdown;