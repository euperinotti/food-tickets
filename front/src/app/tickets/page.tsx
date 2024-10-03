"use client";
import { Button } from "@/components/ui/Button";
import Sidebar from "@/components/ui/Sidebar";
import { Table } from "@/components/ui/Table";
import { useRouter } from "next/navigation";

const tableColumns = [
  { title: "Id", key: "id" },
  { title: "Funcionário", key: "employeeId" },
  { title: "Quantidade", key: "quantity" },
  { title: "Status", key: "status" },
  { title: "Criado em", key: "createdAt" },
  { title: "Atualizado em", key: "updatedAt" },
];

const tableData = [
  {
    id: 1,
    employeeId: 10,
    quantity: 1,
    status: "A",
    createdAt: "2024-12-29 09:51:35",
    updatedAt: "2024-12-29 09:51:35",
  }
];

export default function Index() {
  const router = useRouter();

  return (
    <div className="flex justify-center items-center w-full h-dvh">
      <Sidebar />
      <main className="p-8 flex flex-1 flex-col h-full gap-12">
        <div className="flex gap-4 w-full justify-between">
          <h1 className="text-4xl font-semibold">Tickets</h1>
          <Button
            label={"Novo ticket"}
            styleType="default"
            onClick={() => router.push("/ticket/new")}
          />
        </div>
        <div className="max-h-fit w-full">
          <Table
            columns={tableColumns}
            data={tableData}
            onEditRow={(e) => {
              router.push(`/ticket/${e.id}`);
            }}
          />
        </div>
      </main>
    </div>
  );
}
