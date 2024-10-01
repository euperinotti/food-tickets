import { GenericInputProps } from "../types";

export const InputDate = ({ label, ...rest }: GenericInputProps) => {
  return (
    <div className="flex flex-col">
      <label className="font-semibold text-xs">{label}</label>
      <input
        {...rest}
        type="date"
        className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-1 focus:ring-blue-500 text-sm"
      />
    </div>
  );
};
