package ui;

import controllers.CommentController;
import controllers.JobController;
import controllers.UserController;
import controllers.WorkspaceController;
import enums.JobStatus;
import models.Comment;
import models.Job;
import models.Workspace;

import javax.swing.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UserInterface {

    private final UserController userController = new UserController();

    private final WorkspaceController workspaceController = new WorkspaceController();

    private final JobController jobController = new JobController();

    private final CommentController commentController = new CommentController();

    public UserInterface(){}

    public void application(){
        var user = userController.getUser();
        if(user.getCreatedAt() == null){
            JOptionPane.showMessageDialog(null, "Parece que é sua primeira vez utilizando nosso sistema, pressione ok para inserir seu nome");
            String name = JOptionPane.showInputDialog("Digite seu nome:\n");
            userController.initializeUser(name);
        }
        mainMenu();
    }

    private void mainMenu(){
        var user = userController.getUser();
        String firstMenuOption = user.getWorkspaces() == null || user.getWorkspaces().isEmpty() ? "Você ainda não possui workspaces" : "1 - Acessar workspaces";
        String secondMenuOption = user.getWorkspaces() != null && user.getWorkspaces().size() > 10 ? "Você possui o número máximo de workspaces" : "2 - Criar novo workspace";
        var option = Integer.parseInt(JOptionPane.showInputDialog("Olá "+user.getName()+"!\n"+
                "Qual menu deseja acessar?\n"+
                firstMenuOption+"\n"+
                secondMenuOption+"\n"+
                "\n"+
                "0 - Encerrar programa\n"+
                "\n")
        );
        if(option == 1)
            workspacesMenu();
        if(option == 2)
            workspaceRegisterMenu();
        if(option == 0)
            systemShutdown();
        if(option < 0 || option > 2) {
            JOptionPane.showMessageDialog(null, "Digite uma opção válida\n\n");
            mainMenu();
        }
    }

    private void workspacesMenu(){
        var user = userController.getUser();
        var option = Integer.parseInt(JOptionPane.showInputDialog("Selecione uma opção\n"+
                workspacesMenuOptions(user.getWorkspaces())+
                "\n"+
                "10 - Voltar para o menu principal\n"+
                "11 - Encerrar programa\n"));
        if(option >= 0 && option <= 9){
            try{
                var workspace = user.getWorkspaces().get(option);
                workspaceMenu(workspace.getUuid());
            }
            catch (IndexOutOfBoundsException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Digite uma opção válida\n\n");
                workspacesMenu();
            }
        }
        if(option == 10)
            mainMenu();
        if(option == 11)
            systemShutdown();
        if(option > 11 || option < 0){
            JOptionPane.showMessageDialog(null, "Digite uma opção válida\n");
            workspacesMenu();
        }
    }

    private String workspacesMenuOptions(List<Workspace> workspaceList){
        var workspaces = workspaceList;
        String menuOptions = "Seus workspaces:\n\n";
        for(int count = 0; count <= workspaces.size(); count++)
            menuOptions += count + " - "+workspaces.get(count).getName()+"\n";
        return menuOptions;
    }

    private void workspaceRegisterMenu(){
        var user = userController.getUser();
        if(user.getWorkspaces() != null && user.getWorkspaces().size() > 10){
            JOptionPane.showMessageDialog(null, "Número máximo de workspaces cadastrados\n\n");
            mainMenu();
        }
        String name = JOptionPane.showInputDialog("Digite o nome do workspace: \n");
        String description = JOptionPane.showInputDialog("Digite a descrição do workspace: \n");
        var workspace = workspaceController.createWorkspace(name, description);
        JOptionPane.showMessageDialog(null, "Workspace cadastrado com sucesso\n\n");
        workspaceMenu(workspace.getUuid());
    }

    private void workspaceUpdateMenu(UUID uuid){
        String name = JOptionPane.showInputDialog("Digite o nome do workspace: \n");
        String description = JOptionPane.showInputDialog("Digite a descrição do workspace: \n");
        workspaceController.updateWorkspace(uuid, name, description);
        JOptionPane.showMessageDialog(null, "Workspace atualizado\n\n");
        workspaceMenu(uuid);
    }

    private void workspaceMenu(UUID uuid){
        var workspace = workspaceController.getWorkspace(uuid);
        var option = Integer.parseInt(JOptionPane.showInputDialog(
           workspace.getName()+"\n\n"+
           "Workspaces jobs: \n"+
           workspaceMenuJobsOption(workspace.getJobs())+
           "\n"+
           "10 - Criar novo Job\n"+
           "11 - Editar workspace\n"+
           "12 - Deletar workspace\n"+
           "13 - Workspace report\n"+
           "14 - Voltar para o menu principal\n"+
           "15 - Encerrar programa\n"
        ));
        if(option >= 0 && option <= 9){
            try{
                var job = workspace.getJobs().get(option);
                jobMenu(workspace.getUuid(), job.getUuid());
            }
            catch (IndexOutOfBoundsException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Digite uma opção válida\n\n");
                workspaceMenu(uuid);
            }
        }
        if(option == 10)
            jobRegisterMenu(uuid);
        if(option == 11)
            workspaceUpdateMenu(uuid);
        if(option == 12){
            workspaceController.deleteWorkspace(uuid);
            JOptionPane.showMessageDialog(null, "Workspace deletado com sucesso\n");
            workspacesMenu();
        }
        if(option == 13){

        }
        if(option == 14)
            mainMenu();
        if(option == 15)
            systemShutdown();
        if(option > 15 || option < 0){
            JOptionPane.showMessageDialog(null, "Digite uma opção válida\n");
            workspaceMenu(uuid);
        }
    }

    private String workspaceMenuJobsOption(List<Job> jobList){
        var jobs = jobList;
        Job job;
        String menuOptions = "Seus workspaces:\n\n";
        for(int count = 0; count <= jobs.size(); count++){
            job = jobs.get(count);
            menuOptions += count+ " - "+job.getName()+" - Status: "+job.getStatus()+"\n";
        }
        return menuOptions;
    }

    private void jobRegisterMenu(UUID workspaceUuid){
        var workspace = workspaceController.getWorkspace(workspaceUuid);
        if(workspace.getJobs() != null && workspace.getJobs().size() > 10){
            JOptionPane.showMessageDialog(null, "Número máximo de jobs cadastrados\n\n");
            workspaceMenu(workspaceUuid);
        }
        String name = JOptionPane.showInputDialog("Digite o nome do job: \n");
        String description = JOptionPane.showInputDialog("Digite a descrição do job: \n");
        int dueDate = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de dias até a conclusão do job: \n"));
        long sum = new Date().getTime() + dueDate;
        Date date = new Date(sum);
        var job = jobController.createJob(workspaceUuid, name, description, date);
        JOptionPane.showMessageDialog(null, "Job cadastrado com sucesso\n\n");
        jobMenu(workspaceUuid, job.getUuid());
    }

    private void jobUpdateMenu(UUID workspaceUuid, UUID uuid){
        var job = jobController.getJob(workspaceUuid, uuid);
        if(job.getStatus() == JobStatus.DONE){
            JOptionPane.showMessageDialog(null, "Não é possível alterar status de um Job Done");
            jobMenu(workspaceUuid, uuid);
        }
        String name = JOptionPane.showInputDialog("Digite o nome do job: \n");
        String description = JOptionPane.showInputDialog("Digite a descrição do job: \n");
        int dueDate = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de dias até a conclusão do job: \n"));
        long sum = new Date().getTime() + dueDate;
        Date date = new Date(sum);
        jobController.updateJob(workspaceUuid, uuid, name, description, date);
        JOptionPane.showMessageDialog(null, "Job atualizado com sucesso\n\n");
        jobMenu(workspaceUuid, uuid);
    }

    private void jobStatusUpdateMenu(UUID workspaceUuid, UUID uuid){
        var job = jobController.getJob(workspaceUuid, uuid);
        if(job.getStatus() == JobStatus.DONE){
            JOptionPane.showMessageDialog(null, "Não é possível alterar status de um Job Done");
            jobMenu(workspaceUuid, uuid);
        }
        var option = Integer.parseInt(JOptionPane.showInputDialog(
                "Digite o id de um status: \n\n"+
                "1 - To Do\n"+
                "2 - Development\n"+
                "3 - Done\n"+
                "4 - Stand By\n"
        ));
        if(option == 1){
            jobController.jobToToDo(workspaceUuid, uuid);
            JOptionPane.showMessageDialog(null, "Job atualizado para To Do\n");
            jobMenu(workspaceUuid, uuid);
        }
        if(option == 2){
            jobController.jobToDevelopment(workspaceUuid, uuid);
            JOptionPane.showMessageDialog(null, "Job atualizado para Development\n");
            jobMenu(workspaceUuid, uuid);
        }
        if(option == 3){
            jobController.jobToDone(workspaceUuid, uuid);
            JOptionPane.showMessageDialog(null, "Job atualizado para Done\n");
            jobMenu(workspaceUuid, uuid);
        }
        if(option == 4){
            jobController.jobToStandby(workspaceUuid, uuid);
            JOptionPane.showMessageDialog(null, "Job atualizado para Stand By\n");
            jobMenu(workspaceUuid, uuid);
        }
        if(option < 1 || option > 4){
            JOptionPane.showMessageDialog(null, "Digite uma opção válida\n");
            jobMenu(workspaceUuid, uuid);
        }
    }

    private void jobMenu(UUID workspaceUuid, UUID uuid){
        var job = jobController.getJob(workspaceUuid, uuid);
        var option = Integer.parseInt(JOptionPane.showInputDialog(
           job.getName()+" - Status: "+job.getStatus()+"\n\n"+
           "Comentários do job\n"+
           jobMenuCommentsOptions(job.getComments()) +
           "\n"+
           "10 - Criar novo Comment\n"+
           "11 - Editar job\n"+
           "12 - Editar job status\n"+
           "13 - Deletar job"+
           "14 - Job report"+
           "15 - Voltar para o menu do workspace\n"+
           "16 - Encerrar programa\n"
        ));
        if(option >= 0 && option <= 9){
            try{
                var comment = job.getComments().get(option);
                commentMenu(workspaceUuid, uuid, comment.getUuid());
            }
            catch (IndexOutOfBoundsException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Digite uma opção válida\n\n");
                jobMenu(workspaceUuid, uuid);
            }
        }
        if(option == 10)
            commentRegisterMenu(workspaceUuid, uuid);
        if(option == 11)
            jobUpdateMenu(workspaceUuid, uuid);
        if(option == 12)
            jobStatusUpdateMenu(workspaceUuid, uuid);
        if(option == 13){
            jobController.deleteJob(workspaceUuid, uuid);
            JOptionPane.showMessageDialog(null, "Job deletado com sucesso\n");
            workspaceMenu(workspaceUuid);
        }
        if(option == 14){

        }
        if(option == 15)
            workspaceMenu(workspaceUuid);
        if(option == 16)
            systemShutdown();
        if(option > 16 || option < 0){
            JOptionPane.showMessageDialog(null, "Digite uma opção válida\n");
            jobMenu(workspaceUuid, uuid);
        }
    }

    private String jobMenuCommentsOptions(List<Comment> commentList){
        var comments = commentList;
        Comment comment;
        String menuOptions = "Seus workspaces:\n\n";
        for(int count = 0; count <= comments.size(); count++){
            comment = comments.get(count);
            menuOptions += count+ " - "+comment.getComment()+" - Data: "+comment.getCreatedAt()+"\n";
        }
        return menuOptions;
    }

    private void commentRegisterMenu(UUID workspaceUuid, UUID jobUuid){
        var job = jobController.getJob(workspaceUuid, jobUuid);
        if(job.getComments() != null && job.getComments().size() > 10){
            JOptionPane.showMessageDialog(null, "Número máximo de comments cadastrados\n\n");
            jobMenu(workspaceUuid, jobUuid);
        }
        String comm = JOptionPane.showInputDialog("Digite o comentário: \n");
        var comment = commentController.createComment(workspaceUuid, jobUuid, comm);
        JOptionPane.showMessageDialog(null, "Comment cadastrado com sucesso\n\n");
        commentMenu(workspaceUuid, jobUuid, comment.getUuid());
    }

    private void commentUpdateMenu(UUID workspaceUuid, UUID jobUuid, UUID uuid){
        String comm = JOptionPane.showInputDialog("Digite o comentário: \n");
        var comment = commentController.updateComment(workspaceUuid, jobUuid, uuid, comm);
        JOptionPane.showMessageDialog(null, "Comment atualizado com sucesso\n\n");
        commentMenu(workspaceUuid, jobUuid, uuid);
    }

    private void commentMenu(UUID workspaceUuid, UUID jobUuid, UUID uuid){
        var comment = commentController.getComment(workspaceUuid, jobUuid, uuid);
        var option = Integer.parseInt(JOptionPane.showInputDialog(
                "Comentário: \n\n"+
                comment.getComment()+"\n"+
                "Realizado em: "+comment.getCreatedAt()+"\n"+
                "Atualizado em: "+comment.getUpdatedAt()+"\n\n"+
                "1 - Atualizar comment\n"+
                "2 - Deletar comment\n"+
                "3 - Voltar para o menu do job\n"+
                "4 - Encerrar programa\n"
        ));
        if(option == 1)
            commentUpdateMenu(workspaceUuid, jobUuid, uuid);
        if(option == 2){
            commentController.deleteComment(workspaceUuid, jobUuid, uuid);
            JOptionPane.showMessageDialog(null, "Comment deletado com sucesso\n");
            jobMenu(workspaceUuid, jobUuid);
        }
        if(option == 3)
            jobMenu(workspaceUuid, jobUuid);
        if(option == 4)
            systemShutdown();
        if(option < 1 || option > 4){
            JOptionPane.showMessageDialog(null, "Digite uma opção válida\n");
            commentMenu(workspaceUuid, jobUuid, uuid);
        }
    }

    private void systemShutdown(){
        var user = userController.getUser();
        JOptionPane.showMessageDialog(null, "O sistema será encerrado\n"+"Obrigado "+user.getName()+"!\n\n");
        System.exit(0);
    }

}
