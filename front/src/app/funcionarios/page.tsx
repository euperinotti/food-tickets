"use client";
import Sidebar from "@/components/ui/Sidebar";

const tableColumns = [{ title: "Nome", key: "name" }, {title: "Cpf", key: "cpf"}];
const tableData = [
  { id: 1, name: "John Doe", cpf: "123.456.789-00" },
  { id: 2, name: "Jane Doe", cpf: "987.654.321-00" },
  { id: 2, name: "Jane Doe", cpf: "987.654.321-00" },
  { id: 2, name: "Jane Doe", cpf: "987.654.321-00" },
  { id: 2, name: "Jane Doe", cpf: "987.654.321-00" },
  { id: 2, name: "Jane Doe", cpf: "987.654.321-00" },
  { id: 2, name: "Jane Doe", cpf: "987.654.321-00" },
  { id: 2, name: "Jane Doe", cpf: "987.654.321-00" },
  { id: 2, name: "Jane Doe", cpf: "987.654.321-00" },
  { id: 2, name: "Jane Doe", cpf: "987.654.321-00" },
  { id: 2, name: "Jane Doe", cpf: "987.654.321-00" },
  { id: 2, name: "Jane Doe", cpf: "987.654.321-00" },
  { id: 2, name: "Jane Doe", cpf: "987.654.321-00" },
]

export default function Index() {
  return (
    <div className="flex justify-center items-center w-full h-dvh">
      <Sidebar />
      <main className="p-8 flex flex-1 flex-col h-full gap-12">
        <div className="flex gap-4 w-full justify-between">
          <h1 className="text-4xl font-semibold">Funcionários</h1>
          {/* <FlowbiteButton
            title="Novo funcionário"
            icon={{ component: LuPlus }}
          /> */}
        </div>
        <div className="max-h-fit overflow-y-scroll">
          {/* <FlowbiteTable columns={tableColumns} data={tableData} /> */}
        </div>
      </main>
    </div>
  );
}
