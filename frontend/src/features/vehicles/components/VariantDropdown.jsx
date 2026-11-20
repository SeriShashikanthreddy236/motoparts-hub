function VariantDropdown({
    variants,
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
                Select Variant
            </option>

            {variants.map((variant) => (
                <option
                    key={variant.id}
                    value={variant.id}
                >
                    {variant.name}
                </option>
            ))}
        </select>
    );
}

export default VariantDropdown;