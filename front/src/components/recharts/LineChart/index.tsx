import {
  CartesianGrid,
  Legend,
  Line,
  LineChart,
  ResponsiveContainer,
  Tooltip,
  XAxis,
  YAxis,
} from "recharts";

export interface RechartsLineChartProps {
  title: string;
  data: any[];
  xAxisKey: string;
  lineDataKey: string;
}

export const RechartsLineChart = ({
  title,
  data,
  xAxisKey,
  lineDataKey,
}: RechartsLineChartProps) => {
  return (
    <div className="w-full h-full max-h-[450px] rounded-lg border border-gray-200 bg-white shadow-none disabled:hover gap-2 p-6">
      <span className="text-sm tracking-tight text-gray-400">{title}</span>
      <ResponsiveContainer width="100%" height="100%">
        <LineChart className="w-full" width={100} height={100} data={data}>
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey={xAxisKey} />
          <YAxis />
          <Tooltip />
          <Legend />
          <Line
            type="monotone"
            dataKey={lineDataKey}
            stroke="#8884d8"
            activeDot={{ r: 8 }}
          />
        </LineChart>
      </ResponsiveContainer>
    </div>
  );
};
