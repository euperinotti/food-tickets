import { ButtonProps } from "./types";

export const ButtonType = {
  default:
    "bg-blue-600 border border-blue-600 text-white py-2 px-6 rounded-md hover:bg-blue-700 active:bg-blue-600",
  danger: "bg-gray-100 border border-gray-200 text-red-600 py-2 px-6 rounded-md hover:bg-gray-200 active:bg-gray-300",
  outline: "border border-blue-600 text-blue-600 py-2 px-6 rounded-md"
};

export const Button = ({
  label,
  icon,
  styleType = "default",
  ...rest
}: ButtonProps) => {

  return (
    <button
      {...rest}
      className={`${ButtonType[styleType]} flex items-center gap-4 ${rest.className}`}
    >
      {label}
      {icon && icon({})}
    </button>
  );
};
