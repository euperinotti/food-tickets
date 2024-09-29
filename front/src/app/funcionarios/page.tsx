import { FlowbiteSidebar } from "@/components/flowbite/Sidebar";
import { FlowbiteTable } from "@/components/flowbite/Table";
import { FlowbiteButton } from "@/components/ui/Button";
import { HomeCard } from "@/components/ui/Cards/HomeCard";

export default function Index() {
  return (
    <div className="flex justify-center items-center w-full h-dvh">
      <FlowbiteSidebar />
      <main className="p-8 flex flex-1 flex-col h-full">
        <div className="flex flex-col gap-4 w-full">
          <h1 className="text-4xl font-semibold w-full">Funcionários</h1>
          <div className="flex w-full items-center justify-start gap-4">
            <HomeCard title="Tickets entregues" description="1237" />
            <HomeCard title="Funcionários ativos" description="600" />
            <HomeCard title="Período com mais tickets entregues" description="12/08/2024" />
          </div>
          <FlowbiteButton title="Novo funcionário" />
        </div>
        <div>
          <FlowbiteTable columns={[]} data={[]}/>
        </div>
      </main>
    </div>
  );
}
