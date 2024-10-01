import {
  Combobox,
  ComboboxInput,
  ComboboxOption,
  ComboboxOptions,
} from "@headlessui/react";
import { useState } from "react";

export interface HeadlessComboboxProps<T> {
  data: T[];
  displayKey: string;
  filterKey: string;
}

export const HeadlessCombobox = <T extends { id: number }>({
  data,
  displayKey,
  filterKey,
}: HeadlessComboboxProps<T>) => {
  const [selectedPerson, setSelectedPerson] = useState(data[0]);
  const [query, setQuery] = useState("");

  const filteredData =
    query === ""
      ? data
      : data.filter((item: T) => {
          return (item[filterKey as keyof T] as string)
            .toLowerCase()
            .includes(query.toLowerCase());
        });

  return (
    <Combobox
      value={selectedPerson}
      onChange={setSelectedPerson}
      onClose={() => setQuery("")}
    >
      <ComboboxInput
        aria-label="Assignee"
        displayValue={(item: T) => item[displayKey as keyof T] as string}
        onChange={(event) => setQuery(event.target.value)}
      />
      <ComboboxOptions anchor="bottom" className="border empty:invisible">
        {filteredData.map((item) => (
          <ComboboxOption
            key={item.id}
            value={item}
            className="data-[focus]:bg-blue-100 w-full"
          >
            {item[displayKey as keyof T] as string}
          </ComboboxOption>
        ))}
      </ComboboxOptions>
    </Combobox>
  );
};
