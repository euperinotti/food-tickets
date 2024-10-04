"use client";
import { IEmployee } from "@/@types/IEmployee";
import { ToastStatus } from "@/@types/Toast";
import { API_PROVIDER } from "@/axios/api";
import { Button } from "@/components/ui/Button";
import { InputContainer } from "@/components/ui/Input/InputContainer";
import { InputRadio } from "@/components/ui/Input/InputRadio";
import { InputText } from "@/components/ui/Input/InputText";
import useAlert from "@/hooks/useAlert";
import { BaseTemplate } from "@/template/Base";
import { StringUtils } from "@/utils/StringUtils";
import { useRouter } from "next/router";
import { FormEvent, useEffect, useState } from "react";

const initialState: IEmployee = {
  id: null,
  name: "",
  cpf: "",
  status: "ACTIVE",
  createdAt: new Date().toISOString(),
  updatedAt: new Date().toISOString(),
};

export default function Page() {
  const router = useRouter();
  const { id } = router.query;
  const { notify } = useAlert();
  const [form, setForm] = useState<IEmployee>(initialState);
  const { createEmployee, updateEmployee } = API_PROVIDER;

  useEffect(() => {
    (async () => {
      if (id && id !== "new") {
        const response = await API_PROVIDER.getEmployeeById(id as string);

        setForm(response);
      }
    })();
  }, []);

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    form.cpf = StringUtils.sanitizeCpf(form.cpf);

    if (form.cpf.length != 11) {
      alert("CPF inválido. Informe um CPF válido.");
      return;
    }

    try {
      if (form.id) {
        await updateEmployee({ ...form });
      } else {
        await createEmployee({ ...form });
      }

      notify(
        ToastStatus.SUCCESS,
        form.id
          ? "Funcionário atualizado com sucesso!"
          : "Funcionário criado com sucesso!"
      );

      router.push("/funcionarios");
      return;
    } catch (error: any) {
      notify(ToastStatus.ERROR, error.message);
    }
  };

  return (
    <BaseTemplate>
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
                placeholder="Nome do funcionário"
                value={form.name}
                onChange={(e) =>
                  setForm({ ...form, name: e.target.value.toUpperCase() })
                }
                required
              />
            </InputContainer>
            <InputContainer label="CPF">
              <InputText
                value={form.cpf}
                onChange={(e) =>
                  setForm({
                    ...form,
                    cpf: StringUtils.formatCpf(e.target.value),
                  })
                }
                placeholder="123.456.789-10"
                maxLength={14}
                required
              />
            </InputContainer>
            {form.id && (
              <InputContainer label="Status">
                <InputRadio
                  label="Ativo"
                  name="employee-status"
                  value="ACTIVE"
                  id="employee-active"
                  defaultChecked={form.status === "ACTIVE"}
                  onClick={(e) => setForm({ ...form, status: "ACTIVE" })}
                />
                <InputRadio
                  label="Inativo"
                  name="employee-status"
                  value="INACTIVE"
                  id="employee-inactive"
                  defaultChecked={form.status === "INACTIVE"}
                  onClick={(e) => setForm({ ...form, status: "INACTIVE" })}
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
    </BaseTemplate>
  );
}
