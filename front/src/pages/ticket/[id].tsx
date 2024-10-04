"use client";
import { ITicket } from "@/@types/ITicket";
import { API_PROVIDER } from "@/axios/api";
import { Button } from "@/components/ui/Button";
import { InputContainer } from "@/components/ui/Input/InputContainer";
import { InputNumber } from "@/components/ui/Input/InputNumber";
import { InputRadio } from "@/components/ui/Input/InputRadio";
import { Select } from "@/components/ui/Select";
import { BaseTemplate } from "@/template/Base";
import { useRouter } from "next/router";
import { FormEvent, useEffect, useState } from "react";

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
    name: "Kevin Hart",
    cpf: "123.456.789-00",
    status: "I",
    createdAt: "2024-12-29 09:51:35",
    updatedAt: "2024-12-29 09:51:35",
  },
];

const fetchData = async (id: string) => {
  const response = await API_PROVIDER.getTicketById(id);
  return response;
};

const initialState: ITicket = {
  id: null,
  employeeId: 10,
  quantity: 0,
  status: "A",
  createdAt: new Date().toISOString(),
  updatedAt: new Date().toISOString(),
};

export default function Page() {
  const router = useRouter();
  const { id } = router.query;
  const [form, setForm] = useState<ITicket>(initialState);
  const { createTicket, updateTicket } = API_PROVIDER;

  useEffect(() => {
    (async () => {
      if (id !== "new") {
        const response = await fetchData(id as string);
        setForm(response);
      }
    })();
  }, [id]);

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (form && form.id) {
      await updateTicket({ ...form });
    } else {
      await createTicket({ ...form });
    }
  };

  return (
    <BaseTemplate>
      <main className="flex flex-1 flex-col h-full gap-12">
        <div className="flex gap-4 w-full justify-between">
          <h1 className="text-4xl font-semibold">Ticket</h1>
        </div>
        <div className="h-full w-full overflow-y-scroll">
          <form
            className="bg-white w-[600px] h-auto rounded-md overflow-hidden"
            onSubmit={handleSubmit}
          >
            <div className="flex flex-col item-start justify-start gap-4 p-5">
              <InputContainer label="Funcionário">
                <Select
                  options={{
                    data: tableData,
                    key: "id",
                    label: "name",
                    placeholder: "Selecione um funcionário",
                  }}
                  onChange={(e) =>
                    setForm({ ...form, employeeId: Number(e.target.value) })
                  }
                  value={form.employeeId}
                />
              </InputContainer>
              <InputContainer label="Quantidade">
                <InputNumber
                  min={0}
                  value={form.quantity}
                  onChange={(e) =>
                    setForm({ ...form, quantity: Number(e.target.value) })
                  }
                />
              </InputContainer>
              {form.id && (
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
              )}
            </div>
            <div className="flex item-start justify-end gap-4 p-5">
              <Button
                label="Limpar"
                styleType="danger"
                type="reset"
                onClick={() => setForm(initialState)}
              />
              <Button label="Salvar" styleType="default" type="submit" />
            </div>
          </form>
        </div>
      </main>
    </BaseTemplate>
  );
}