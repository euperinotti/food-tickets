import { SelectHTMLAttributes } from "react";

export interface SelectProps extends SelectHTMLAttributes<HTMLSelectElement> {
  options: {
    key: string;
    label: string;
    placeholder: string;
    data: any[];
  };
}

export const Select = ({ options, ...rest }: SelectProps) => {
  return (
    <select
      {...rest}
      className="w-full px-3 py-2 border-2 border-slate-200 rounded-lg focus:outline-none focus:ring-1 focus:ring-blue-500 appearance-none"
    >
      <option value="">{options.placeholder}</option>
      {options.data.map((item) => (
        <option
          key={item[options.key]}
          value={item[options.key]}
          selected={rest.value == item[options.key]}
        >
          {item.name}
        </option>
      ))}
    </select>
  );
};
