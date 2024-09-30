import { GenericInputProps } from "../types";

export const InputRadio = ({ label, ...rest }: GenericInputProps) => {
  return (
    <div>
      <input {...rest} type="radio" id={rest.id} className="w-4 hidden peer" />
      <label
        className="flex gap-2 text-lg p-4 border border-gray-200 rounded-md bg-gray-50 peer-checked:border-blue-400 peer-checked:bg-blue-50 peer-checked:text-blue-500"
        htmlFor={rest.id}
      >
        {label}
      </label>
    </div>
  );
};
