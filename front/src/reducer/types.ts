import { TableConfig } from "@/components/ui/Table/types";

export interface DataTableReducerProps<T> {
  config: TableConfig<T>;
  currentPage: number;
  paginate: (page: number) => void;
  totalPages: number;
}
