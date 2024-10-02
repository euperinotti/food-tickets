import { forwardRef } from "react";
import { LuChevronLeft, LuChevronRight } from "react-icons/lu";
import { useReactToPrint } from "react-to-print";
import { Button } from "../../Button";
import { TableFooterButton } from "./Button";

export interface TableFooterProps {
  config: any;
  currentPage: number;
  paginate: (page: number) => void;
  totalPages: number;
}

export const TableFooter = forwardRef(
  ({ config, currentPage, paginate, totalPages }: TableFooterProps, ref) => {
    const handlePrint = useReactToPrint({
      contentRef: ref as any,
    });

    const handleDownloadReport = () => {
      handlePrint({
        contentRef: ref as any,
      });
    };

    return (
      <div className="flex justify-between items-center mt-4">
        <span className="text-sm text-gray-700 dark:text-gray-400">
          Showing {config.rowsPerPage * (currentPage - 1) + 1} to{" "}
          {Math.min(config.rowsPerPage * currentPage, config.sortedData.length)}{" "}
          of {config.sortedData.length} entries
        </span>
        <div className="flex items-stretch justify-center gap-4">
          <Button
            label="Exportar registros"
            styleType="outline"
            onClick={handleDownloadReport}
          />
          <div className="inline-flex gap-2">
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
      </div>
    );
  }
);
