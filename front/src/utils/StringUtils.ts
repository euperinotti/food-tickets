export const StringUtils = {
  formatCpf(value: string) {
    return value
      .replace(/\D/g, "")
      .replace(/(\d{3})(\d)/, "$1.$2")
      .replace(/(\d{3})(\d)/, "$1.$2")
      .replace(/(\d{3})(\d{1,2})$/, "$1-$2")
      .slice(0, 14);
  },
  sanitizeCpf(str: string): string {
    return str.replace(/\D/g, "");
  },
};
