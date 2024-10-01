"use client";
import { IEmployee } from "@/@types/IEmployee";
import { Button } from "@/components/ui/Button";
import { InputContainer } from "@/components/ui/Input/InputContainer";
import { InputRadio } from "@/components/ui/Input/InputRadio";
import { InputText } from "@/components/ui/Input/InputText";
import Sidebar from "@/components/ui/Sidebar";
import { useEmployee } from "@/hooks/useEmployee";
import { FormEvent, useEffect, useState } from "react";

const fetchData = async (id: string) => {
  // const response = await API_PROVIDER.getEmployeeById(id);
  // if (!response.ok) {
  //   throw new Error("Falha ao buscar os dados");
  // }

  return {
    id: 1,
    name: "John Doe",
    cpf: "123.456.789-00",
    status: "A",
    createdAt: "2024-12-29 09:51:35",
    updatedAt: "2024-12-29 09:51:35",
  };
};

const initialState: IEmployee = {
  id: null,
  name: "",
  cpf: "",
  status: "A",
  createdAt: new Date().toISOString(),
  updatedAt: new Date().toISOString(),
};

export default function Page({ params }: { params: { id: string } }) {
  const { id } = params;
  const [form, setForm] = useState<IEmployee>(initialState);
  const { addEmployee, updateEmployee } = useEmployee();

  useEffect(() => {
    (async () => {
      if (id !== "new") {
        const response = await fetchData(id);

        setForm(response);
      }
    })();
  }, [id]);

  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (form && form.id) {
      updateEmployee({ ...form });
    } else {
      addEmployee({ ...form });
    }
  };

  return (
    <div className="flex justify-center items-center w-full h-dvh">
      <Sidebar />
      <main className="p-8 flex flex-1 flex-col h-full gap-12">
        <div className="flex gap-4 w-full justify-between">
          <h1 className="text-4xl font-semibold">Funcionário</h1>
        </div>
        <div className="h-full w-full overflow-y-scroll">
          <form
            className="bg-white w-[600px] h-auto rounded-md overflow-hidden"
            onSubmit={handleSubmit}
          >
            <div className="flex flex-col item-start justify-start gap-4 p-5">
              <InputContainer label="Nome">
                <InputText
                  value={form.name}
                  onChange={(e) => setForm({ ...form, name: e.target.value })}
                />
              </InputContainer>
              <InputContainer label="CPF">
                <InputText
                  value={form.cpf}
                  onChange={(e) => setForm({ ...form, cpf: e.target.value })}
                />
              </InputContainer>
              <InputContainer label="Status">
                <InputRadio
                  label="Ativo"
                  name="employee-status"
                  value="A"
                  id="employee-active"
                  defaultChecked={form.status === "A"}
                  onClick={(e) => setForm({ ...form, status: "A" })}
                />
                <InputRadio
                  label="Inativo"
                  name="employee-status"
                  value="I"
                  id="employee-inactive"
                  defaultChecked={form.status === "I"}
                  onClick={(e) => setForm({ ...form, status: "I" })}
                />
              </InputContainer>
            </div>
            <div className="flex item-start justify-end gap-4 p-5">
              <Button label="Limpar" styleType="danger" type="reset" />
              <Button label="Salvar" styleType="default" type="submit" />
            </div>
          </form>
        </div>
      </main>
    </div>
  );
}
