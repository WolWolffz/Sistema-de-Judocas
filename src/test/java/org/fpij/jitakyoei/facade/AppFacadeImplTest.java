// package org.fpij.jitakyoei.facade;

// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertFalse;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.util.ArrayList;
// import java.util.List;

// import org.fpij.jitakyoei.business.AlunoBO;
// import org.fpij.jitakyoei.business.AlunoBOImpl;
// import org.fpij.jitakyoei.business.EntidadeBO;
// import org.fpij.jitakyoei.business.EntidadeBOImpl;
// import org.fpij.jitakyoei.business.ProfessorBO;
// import org.fpij.jitakyoei.business.ProfessorBOImpl;
// import org.fpij.jitakyoei.business.ProfessorEntidadeBO;
// import org.fpij.jitakyoei.business.ProfessorEntidadeBOImpl;
// import org.fpij.jitakyoei.model.beans.Aluno;
// import org.fpij.jitakyoei.model.beans.Filiado;
// import org.fpij.jitakyoei.model.dao.DAO;
// import org.fpij.jitakyoei.view.AppView;
// import org.junit.Before;
// import org.junit.Test;

// public class AppFacadeImplTest {
//     private AppView mockView;
// 	private AlunoBO mockAlunoBO;
// 	private ProfessorBO mockProfessorBO;
// 	private EntidadeBO mockEntidadeBO;
// 	private ProfessorEntidadeBO mockProfessorEntidadeBO;
    
//     private AppFacadeImpl appFacade;

//     @Before
//     public void setUp(){
//         mockView = mock(AppView.class);
//         mockAlunoBO = mock(AlunoBOImpl.class);
//         mockProfessorBO = mock(ProfessorBO.class);
// 		mockEntidadeBO = mock(EntidadeBO.class);
// 		mockProfessorEntidadeBO = mock(ProfessorEntidadeBO.class);

//         appFacade = new AppFacadeImpl(mockView);
//     }

//     @Test
//     public void testCreateAluno(){
//         Aluno aluno = new Aluno();
//         verify(appFacade).createAluno(aluno);
//     }

//     @Test
//     public void testUpdateAluno(){
//         Aluno oldAluno = new Aluno();
//         Aluno newAluno = new Aluno();

//         appFacade.createAluno(oldAluno);

//         verify(appFacade).updateAluno(newAluno);
//     }

//     @Test
//     public void testSearchAluno(){
//         Aluno aluno = new Aluno();
//         appFacade.createAluno(aluno);
//         verify(appFacade).searchAluno(aluno);
//     }

//     @Test
//     public void testListAluno(){
//         verify(appFacade).listAlunos();

//         List<Aluno> mockResult = new ArrayList<>();
//         mockResult.add(new Aluno());
//         mockResult.add(new Aluno());

//         when(appFacade.listAlunos()).thenReturn(mockResult);

//         List<Aluno> result = appFacade.listAlunos();

//         verify(appFacade, times(1)).listAlunos();

//         assertEquals(2, result.size());
//     }

// }
