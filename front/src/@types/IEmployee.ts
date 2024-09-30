export interface IEmployee {
  id: number;
  name: string;
  cpf: string;
  status: EmployeeStatus;
  createdAt: string;
  updatedAt: string;
}

export type EmployeeStatus = "A" | "I";
