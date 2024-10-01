export interface ITicket {
  id: number | null;
  employeeId: number;
  status: TicketStatus;
  createdAt: string;
  updatedAt: string;
}

export type TicketStatus = "A" | "I";
