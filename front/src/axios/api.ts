import { IEmployee } from "@/@types/IEmployee";
import { ITicket } from "@/@types/ITicket";
import api from "./config";

export const API_PROVIDER = {
  createEmployee: async (data: IEmployee) => {
    const res = await api.post("/employees", JSON.stringify(data));
    return res.data;
  },
  updateEmployee: async (data: IEmployee) => {
    const res = await api.put(`/employees/${data.id}`, JSON.stringify(data));
    return res.data;
  },
  getEmployees: async () => {
    const res = await api.get("/employees");
    const data = res.data;

    return data;
  },
  getActiveEmployees: async () => {
    const res = await api.get("/employees/status/A");
    const data = res.data;

    return data;
  },
  getEmployeeById: async (id: string) => {
    const res = await api.get(`/employees/${id}`);
    const data = res.data;

    return data;
  },
  createTicket: async (data: ITicket) => {
    const res = await api.post("/tickets", JSON.stringify(data));
    return res.data;
  },
  updateTicket: async (data: ITicket) => {
    const res = await api.put(`/tickets/${data.id}`, JSON.stringify(data));
    return res.data;
  },
  getTickets: async () => {
    const res = await api.get("/tickets");
    const data = res.data;

    return data;
  },
  getTicketById: async (id: string) => {
    const res = await api.get(`/tickets/${id}`);
    const data = res.data;

    return data;
  },
  getAnalytics: async () => {
    const res = await api.get(`/analytics`);
    const data = {
      ...res.data,
      employeeWithMostTickets: res.data.employeeWithMostTickets?.name,
    };

    return data;
  },
};

export default api;
