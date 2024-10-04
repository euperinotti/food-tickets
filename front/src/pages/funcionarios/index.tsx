import { IEmployee } from "@/@types/IEmployee";
import { API_PROVIDER } from "@/axios/api";
import { Button } from "@/components/ui/Button";
import { Table } from "@/components/ui/Table";
import useAlert from "@/hooks/useAlert";
import { BaseTemplate } from "@/template/Base";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const tableColumns = [
  { title: "Nome", key: "name" },
  { title: "Cpf", key: "cpf" },
  { title: "Status", key: "status" },
  { title: "Criado em", key: "createdAt" },
  { title: "Atualizado em", key: "updatedAt" },
];

export default function Index() {
  const router = useRouter();
  const { notify } = useAlert();
  const [data, setData] = useState<IEmployee[]>([]);

  useEffect(() => {
    (async () => {
      const response = await API_PROVIDER.getEmployees();
      setData(response);
    })();
  }, []);

  return (
    <BaseTemplate>
      <div className="p-2 flex flex-col gap-6">
        <div className="flex gap-4 w-full justify-between">
          <h1 className="text-4xl font-semibold">Funcionários</h1>
          <Button
            label={"Novo funcionário"}
            styleType="default"
            onClick={() => router.push("/funcionario/new")}
          />
        </div>
        <div className="max-h-fit w-full">
          <Table
            columns={tableColumns}
            data={data}
            onEditRow={(e) => {
              router.push(`/funcionario/${e.id}`);
            }}
          />
        </div>
      </div>
    </BaseTemplate>
  );
}
