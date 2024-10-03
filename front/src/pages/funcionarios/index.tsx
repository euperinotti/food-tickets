import { Button } from "@/components/ui/Button";
import { Table } from "@/components/ui/Table";
import { BaseTemplate } from "@/template/Base";
import { useRouter } from "next/navigation";

const tableColumns = [
  { title: "Nome", key: "name" },
  { title: "Cpf", key: "cpf" },
  { title: "Status", key: "status" },
  { title: "Criado em", key: "createdAt" },
  { title: "Atualizado em", key: "updatedAt" },
];

const tableData = [
  {
    id: 1,
    name: "John Doe",
    cpf: "123.456.789-00",
    status: "A",
    createdAt: "2024-12-29 09:51:35",
    updatedAt: "2024-12-29 09:51:35",
  },
  {
    id: 1,
    name: "John Doe",
    cpf: "123.456.789-00",
    status: "I",
    createdAt: "2024-12-29 09:51:35",
    updatedAt: "2024-12-29 09:51:35",
  },
];

export default function Index() {
  const router = useRouter();

  return (
    <BaseTemplate>
      <div className="flex flex-col gap-6">
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
            data={tableData}
            onEditRow={(e) => {
              router.push(`/funcionario/${e.id}`);
            }}
          />
        </div>
      </div>
    </BaseTemplate>
  );
}
