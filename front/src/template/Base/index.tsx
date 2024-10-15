import { Layout } from "@/components/shadcn/custom/layout";
import Sidebar from "@/components/shadcn/sidebar";
import { UserNav } from "@/components/shadcn/user-nav";
import { BaseTemplateProps } from "./types";

export const BaseTemplate = ({ children }: BaseTemplateProps) => {
  return (
    <Layout>
      <Layout.Header sticky>
        {/* <Search /> */}
        <div className='ml-auto flex items-center space-x-4'>
          <UserNav />
        </div>
      </Layout.Header>
      <Layout.Body>
        <Sidebar isCollapsed={true} setIsCollapsed={() => {}}/> {children}</Layout.Body>
    </Layout>
  );
};
