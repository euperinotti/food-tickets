export interface IAnalytics {
  activeEmployees: number;
  ticketsRetrieved: number;
  dayWithMostTickets: string;
  employeeWithMostTickets: string | null;
  twoWeeksTicketsHistory: any[];
}
