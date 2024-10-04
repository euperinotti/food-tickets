import { EmployeeStatus } from "@/@types/IEmployee";
import { DataTableReducerProps } from "./types";

export const DataTableReducer = <T extends object>({
  config,
  currentPage,
  paginate,
  totalPages,
}: DataTableReducerProps<T>) => {

  const renderStatusPill = (status: EmployeeStatus) => {
    const isActive = status === "ACTIVE";
    const statusClasses = isActive
      ? "bg-green-100 text-green-800"
      : "bg-red-100 text-red-800";

    return (
      <span
        className={`px-2 inline-flex text-xs leading-5 font-semibold rounded-full ${statusClasses}`}
      >
        {status === "ACTIVE" ? "ATIVO" : "INATIVO"}
      </span>
    );
  };

  return {
    renderStatusPill,
  };
};
