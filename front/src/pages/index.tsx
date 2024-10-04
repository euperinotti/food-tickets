import { IAnalytics } from "@/@types/IAnalytics";
import { ToastStatus } from "@/@types/Toast";
import { API_PROVIDER } from "@/axios/api";
import { RechartsLineChart } from "@/components/recharts/LineChart";
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
      <h1 className="text-4xl font-semibold w-full">Dashboard</h1>
      <div className="flex w-full items-stretch justify-start gap-4">
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
          description={analytics.dayWithMostTickets ? new Date(
            analytics.dayWithMostTickets
          ).toLocaleDateString() : ""}
        />
      </div>
      <HomeCard
        title="Funcionário com mais tickets"
        description={analytics.employeeWithMostTickets || ""}
        className="min-w-full"
      />
      <RechartsLineChart
        title="Histórico de tickets nas últimas duas semanas"
        xAxisKey="createdAt"
        lineDataKey="quantity"
        data={analytics.twoWeeksTicketsHistory}
      />
    </BaseTemplate>
  );
}
