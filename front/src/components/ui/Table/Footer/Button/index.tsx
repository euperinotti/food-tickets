import { TableFooterButtonProps } from "./types";

export const TableFooterButton = ({
  content,
  ...rest
}: TableFooterButtonProps) => {
  return (
    <button
      {...rest}
      className={`px-4 py-2 text-sm font-medium text-white bg-white rounded-md hover:bg-slate-300 ${rest.className}`}
    >
      {content}
    </button>
  );
};
