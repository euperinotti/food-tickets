import { ButtonProps } from "./types";

export const ButtonType = {
  default:
    "bg-blue-700 border border-blue-600 text-white py-2 px-6 rounded hover:bg-blue-800 active:bg-blue-600",
  danger: "bg-gray-100 border border-gray-200 text-red-600 py-2 px-6 rounded hover:bg-gray-200 active:bg-gray-300",
};

export const Button = ({
  label,
  icon,
  iconClassNames,
  styleType = "default",
  ...rest
}: ButtonProps) => {

  return (
    <button
      {...rest}
      className={`${ButtonType[styleType]} font-bold flex items-center gap-4 ${rest.className}`}
    >
      {label}
      {icon}
    </button>
  );
};
