import { GenericInputProps } from "../types";

export const InputNumber = ({ ...rest }: GenericInputProps) => {
  return (
    <input
      type="number"
      className="border-2 border-slate-200 rounded-md outline-none px-2 py-1 focus:outline-blue-200 focus:outline-offset-0"
      {...rest}
    />
  );
};
