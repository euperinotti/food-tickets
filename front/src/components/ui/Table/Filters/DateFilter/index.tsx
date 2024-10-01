import { Button } from "@/components/ui/Button";
import { InputDate } from "@/components/ui/Input/InputDate";
import { Popover, PopoverButton, PopoverPanel } from "@headlessui/react";
import { useState } from "react";
import { DateFilterProps } from "./types";
export const DateFilter = ({ onFilter }: DateFilterProps) => {
  const [date, setDate] = useState<{
    start: Date | null;
    end: Date | null;
  }>({
    start: null,
    end: null,
  });

  const handleFilter = () => {
    onFilter(date.start, date.end);
  };

  return (
    <Popover className="relative right-0">
      <PopoverButton className="p-2 text-blue-600 hover:bg-blue-100 rounded">
        Filtros
      </PopoverButton>
      <PopoverPanel className="absolute z-10 right-0 bg-white border border-blue-200 rounded-lg shadow-md p-4">
        <h3 className="font-semibold text-blue-600 mb-4">Filtro por Data</h3>
        <div className="flex items-end justify-center gap-4">
          <InputDate
            label="Data inicial"
            onChange={(e) =>
              setDate({
                ...date,
                start: e.target.value ? new Date(e.target.value) : null,
              })
            }
          />
          <InputDate
            label="Data final"
            onChange={(e) =>
              setDate({
                ...date,
                end: e.target.value ? new Date(e.target.value) : null,
              })
            }
          />
          <Button onClick={handleFilter} label="Filtrar" styleType="default" />
        </div>
      </PopoverPanel>
    </Popover>
  );
};
