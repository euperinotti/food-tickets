import { IAnalytics } from "@/@types/IAnalytics";
import { ToastStatus } from "@/@types/Toast";
import { API_PROVIDER } from "@/axios/api";
import { HomeCard } from "@/components/ui/Cards/HomeCard";
import useAlert from "@/hooks/useAlert";
import { BaseTemplate } from "@/template/Base";
import { useEffect, useState } from "react";

const initialState: IAnalytics = {
  activeEmployees: 0,
  ticketsRetrieved: 0,
  dayWithMostTickets: "",
  employeeWithMostTickets: null,
  twoWeeksTicketsHistory: [],
};

export default function Home() {
  const [analytics, setAnalytics] = useState<IAnalytics>(initialState);
  const { notify } = useAlert();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await API_PROVIDER.getAnalytics();
        setAnalytics(response);
      } catch (error) {
        notify(ToastStatus.ERROR, error.message);
      }
    };

    fetchData();
  }, []);

  return (
    <BaseTemplate>
      <div className="flex flex-col gap-4 w-full">
        <h1 className="text-4xl font-semibold w-full">Dashboard</h1>
        <div className="flex w-full items-center justify-start gap-4">
          <HomeCard
            title="Tickets entregues"
            description={analytics.ticketsRetrieved}
          />
          <HomeCard
            title="Funcionários ativos"
            description={analytics.activeEmployees}
          />
          <HomeCard
            title="Período com mais tickets entregues"
            description={analytics.dayWithMostTickets}
          />
        </div>
      </div>
    </BaseTemplate>
  );
}
