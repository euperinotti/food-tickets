"use client";
import { IEmployee } from "@/@types/IEmployee";
import { ITicket } from "@/@types/ITicket";
import { ToastStatus } from "@/@types/Toast";
import { API_PROVIDER } from "@/axios/api";
import { Button } from "@/components/ui/Button";
import { InputContainer } from "@/components/ui/Input/InputContainer";
import { InputNumber } from "@/components/ui/Input/InputNumber";
import { InputRadio } from "@/components/ui/Input/InputRadio";
import { Select } from "@/components/ui/Select";
import useAlert from "@/hooks/useAlert";
import { BaseTemplate } from "@/template/Base";
import { useRouter } from "next/router";
import { FormEvent, useEffect, useState } from "react";

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
  const { notify } = useAlert();
  const [form, setForm] = useState<ITicket>(initialState);
  const [employees, setEmployees] = useState<IEmployee[]>([]);
  const { createTicket, updateTicket, getActiveEmployees, getTicketById } = API_PROVIDER;

  useEffect(() => {
    (async () => {
      try {
        const response = await getActiveEmployees();
        setEmployees(response);
      } catch (error) {
        notify(
          ToastStatus.ERROR,
          "Não foi possível buscar lista de funcionários"
        );
      }
    })();

    (async () => {
      if (id !== "new") {
        const response = await getTicketById(id as string);
        setForm(response);
      }
    })();
  }, [id]);

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    try {
      if (form && form.id) {
        await updateTicket({ ...form });
      } else {
        await createTicket({ ...form });
      }

      notify(
        ToastStatus.SUCCESS,
        form.id
          ? "Ticket atualizado com sucesso!"
          : "Ticket criado com sucesso!"
      );
    } catch (error: any) {
      notify(ToastStatus.ERROR, error.message);
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
                    data: employees,
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
