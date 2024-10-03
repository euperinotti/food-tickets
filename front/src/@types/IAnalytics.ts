import { IEmployee } from "./IEmployee";

export interface IAnalytics {
  activeEmployees: number;
  ticketsRetrieved: number;
  dayWithMostTickets: string;
  employeeWithMostTickets: IEmployee | null;
  twoWeeksTicketsHistory: any[];
}
