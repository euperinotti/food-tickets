export interface IEmployee {
  id: number | null;
  name: string;
  cpf: string;
  status: EmployeeStatus;
  createdAt: string;
  updatedAt: string;
}

export type EmployeeStatus = "A" | "I";
