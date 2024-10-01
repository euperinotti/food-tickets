import { IEmployee } from "@/@types/IEmployee";
import api from "./config";

export const API_PROVIDER = {
  createEmployee: async (data: IEmployee) => {
    const res = await api.post("/employee", JSON.stringify(data));
    return res.data;
  },
  getEmployees: async () => {
    const res = await api.get("/employees");
    const data = res.data;

    return data;
  },
};

export default api;
