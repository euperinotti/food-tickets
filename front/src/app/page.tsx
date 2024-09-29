import { HomeCard } from "@/components/ui/Cards/HomeCard";
import Sidebar from "@/components/ui/Sidebar";

export default function Home() {
  return (
    <div className="flex justify-center items-center w-full h-dvh">
      <Sidebar />
      <main className="p-8 flex flex-1 flex-col h-full">
        <div className="flex flex-col gap-4 w-full">
          <h1 className="text-4xl font-semibold w-full">Dashboard</h1>
          <div className="flex w-full items-center justify-start gap-4">
            <HomeCard title="Tickets entregues" description="1237" />
            <HomeCard title="Funcionários ativos" description="600" />
            <HomeCard title="Período com mais tickets entregues" description="12/08/2024" />
          </div>
        </div>
      </main>
    </div>
  );
}
