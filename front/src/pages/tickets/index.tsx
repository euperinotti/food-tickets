"use client";
import { ITicket } from "@/@types/ITicket";
import { ToastStatus } from "@/@types/Toast";
import { API_PROVIDER } from "@/axios/api";
import { Button } from "@/components/ui/Button";
import { Table } from "@/components/ui/Table";
import useAlert from "@/hooks/useAlert";
import { BaseTemplate } from "@/template/Base";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const tableColumns = [
  { title: "Id", key: "id" },
  { title: "Funcionário", key: "employeeId" },
  { title: "Quantidade", key: "quantity" },
  { title: "Status", key: "status" },
  { title: "Criado em", key: "createdAt" },
  { title: "Atualizado em", key: "updatedAt" },
];

export default function Index() {
  const router = useRouter();
  const { notify } = useAlert();
  const [data, setData] = useState<ITicket[] | []>([])

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await API_PROVIDER.getTickets();
        setData(response);
      } catch (error) {
        notify(ToastStatus.ERROR, error.message);
      }
    };

    fetchData();
  }, []);

  return (
    <BaseTemplate>
      <main className="flex flex-1 flex-col h-full gap-12">
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
            data={data}
            onEditRow={(e) => {
              router.push(`/ticket/${e.id}`);
            }}
          />
        </div>
      </main>
    </BaseTemplate>
  );
}
