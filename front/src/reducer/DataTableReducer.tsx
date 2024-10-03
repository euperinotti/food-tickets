import { EmployeeStatus } from "@/@types/IEmployee";
import { Button } from "@/components/ui/Button";
import { TableFooterButton } from "@/components/ui/Table/Footer/Button";
import { LuChevronLeft, LuChevronRight } from "react-icons/lu";
import { DataTableReducerProps } from "./types";

export const DataTableReducer = <T extends object>({
  config,
  currentPage,
  paginate,
  totalPages,
}: DataTableReducerProps<T>) => {

  const renderTableFooter = () => {
    return (
      <div className="flex justify-between items-center mt-4">
        <span className="text-sm text-gray-700 dark:text-gray-400">
          Showing {config.rowsPerPage * (currentPage - 1) + 1} to{" "}
          {Math.min(config.rowsPerPage * currentPage, config.sortedData.length)}{" "}
          of {config.sortedData.length} entries
        </span>
        <Button
          label="Exportar registros"
          styleType="outline"
          onClick={() => {
            downloadReport(config.sortedData);
          }}
        />
        <div className="inline-flex mt-2 xs:mt-0 gap-2">
          <TableFooterButton
            content={<LuChevronLeft className="text-slate-800" />}
            onClick={() => paginate(currentPage - 1)}
            disabled={currentPage === 1}
            className={`${
              currentPage === totalPages && "opacity-50 cursor-not-allowed"
            }`}
          />
          {[...Array(totalPages).keys()].map((num) => (
            <button
              key={num + 1}
              onClick={() => paginate(num + 1)}
              className={`px-4 py-2 text-sm font-medium rounded-md ${
                currentPage === num + 1
                  ? "text-blue-600"
                  : "text-white border border-gray-300 hover:bg-gray-100"
              }`}
            >
              {num + 1}
            </button>
          ))}
          <TableFooterButton
            content={<LuChevronRight className="text-slate-800" />}
            onClick={() => paginate(currentPage + 1)}
            disabled={currentPage === totalPages}
            className={`${
              currentPage === totalPages && "opacity-50 cursor-not-allowed"
            }`}
          />
        </div>
      </div>
    );
  };

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
    renderTableFooter,
    renderStatusPill,
  };
};
