package demo;

import entities.*;
import models.AlunoModel;
import models.CursoModel;

public class GestaoCursosMain {

    public static void main(String[] args) {
        AlunoModel alunoModel = new AlunoModel();

        // Criar Aluno
        Aluno aluno = new Aluno();
        aluno.setNomeCompleto("João da Silva");
        aluno.setMatricula("123456");
        aluno.setEmail("joao.silva@email.com");

        // Criar Endereco
        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua das Flores");
        endereco.setNumero("123");
        endereco.setBairro("Centro");
        endereco.setCidade("São Paulo");
        endereco.setEstado("SP");
        endereco.setCep(12345678);
        endereco.setAluno(aluno);
        aluno.getEnderecos().add(endereco);

        // Criar Telefone
        Telefone telefone = new Telefone();
        telefone.setDDD("11");
        telefone.setNumero("912345678");
        telefone.setAluno(aluno);
        aluno.getTelefones().add(telefone);

        alunoModel.create(aluno);

        CursoModel cursoModel = new CursoModel();

        // Criar Professor
        Professor professor = new Professor();
        professor.setNomeCompleto("Maria Souza");
        professor.setMatricula("987654");
        professor.setEmail("maria.souza@email.com");

        // Criar Curso
        Curso curso = new Curso();
        curso.setNome("Engenharia de Software");
        curso.setSigla("ES");
        curso.setProfessor(professor);
        curso.getAlunos().add(aluno);

        // Criar MaterialCurso
        MaterialCurso materialCurso = new MaterialCurso();
        materialCurso.setUrl("http://www.exemplo.com.br/cursos/es");


        curso.setProfessor(professor);
        curso.setMaterialCurso(materialCurso);
        curso.getAlunos().add(aluno);

        System.out.println("Dados persistidos com sucesso!");

        cursoModel.create(curso);

        // Testar operações CRUD
        Aluno alunoEncontrado = alunoModel.findById(aluno.getId());
        System.out.println("Aluno encontrado: " + alunoEncontrado.getNomeCompleto());

    }
}