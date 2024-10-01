import { GenericInputProps } from "../types";

export const InputText = ({ ...rest }: GenericInputProps) => {
  return (
    <input
      type="text"
      className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-1 focus:ring-blue-500"
      {...rest}
    />
  );
};
