module F2 where
 
import Data.List
import Data.Char
 
data Type = 
    DNA | Protein 
        deriving (Show, Eq)
 
data MolSeq = 
        MolSeq { molName :: [Char]
                        , molSeq :: [Char]
                        , molType :: Type} 
                        deriving (Show, Eq)
 
seqName :: MolSeq -> [Char]
seqName m = molName m
 
seqSequence :: MolSeq -> [Char]
seqSequence m = molSeq m
 
seqLength :: MolSeq -> Int
seqLength m = length(seqSequence m)
 
 
string2seq :: [Char] -> [Char] -> MolSeq
string2seq n s 
        |s == [] = error "string2seq: empty sequence"
        |n == [] = error "string2seq: empty name"
        |otherwise = MolSeq { molName = n
                            , molSeq = s
                            , molType = checkType s}
 
 
isDNA (c) = elem c ['A', 'C', 'G', 'T','a','c','g','t']
 
-- Kontrollerar vilken typ sekvensen är genom att gå igenom hela sekvensen och ta hjälp av isDNA
checkType :: [Char] -> Type
checkType (x:xs)
        |(isDNA x == True) && (xs == []) = DNA
        |(isDNA x == True) && (xs /= []) = checkType xs
        |otherwise = Protein
 
--------------------------------------------------------
 
-- Tar in två MolSeqs och jämför dess sekvenser för att hitta antalet element som skiljer sig för att räkna ut avståndet mellan dem
-- Tar hjälp av seqAlpha för att räkna ut antal skiljeelement, seqComare för att se att de är jämförbara (typ och längd) 
-- Sedan räknar seqCalc ut avståndet mellan dem mha alpha-värdet och typen
seqDistance :: MolSeq -> MolSeq -> Double
seqDistance a b
        | (seqSequence a) == [] || (seqSequence b) == [] =  error "Both sequences need to be longer than 0 charaters"
        | (seqCompare a b) == True = 
                seqCalc ((seqAlpha 0.0 (seqSequence a) (seqSequence b))/ (fromIntegral (length (seqSequence a)))) (checkType(seqSequence a))
        |otherwise = error "Both sequences need to be same type and length"
 
seqAlpha ::Double -> [Char] -> [Char] -> Double
seqAlpha n [] [] = n
seqAlpha n (x:xs) (y:ys)
        |(x == y) = seqAlpha n xs ys
        |otherwise = seqAlpha (n+1.0) xs ys
 
seqCompare :: MolSeq -> MolSeq -> Bool
seqCompare a b
        |(checkType(seqSequence a) == checkType(seqSequence b)) && (length(seqSequence a) == length(seqSequence b)) = True
        |otherwise = False
 
seqCalc :: Double -> Type -> Double
seqCalc a molSeqtype
        | a == 0.0 = 0.0
        |(a <= 0.74) && (molSeqtype == DNA) = ((-3/4)*log(1-((4*a)/3)))
        |(a > 0.74) && (molSeqtype == DNA) = 3.3
        |(a <= 0.94) && (molSeqtype == Protein) = ((-19/20)*log(1-((20*a)/19)))
        |(a > 0.94) && (molSeqtype == Protein) = 3.7
        |otherwise = error "Something went wrong during seqCalculation"
 
--------------------------------------------------------
 
data Profile = Profile 
        { proName :: [Char]
        , proMatrix :: [[(Char, Int)]] 
        , proAmount :: Double
        , proType :: Type } 
        deriving (Show, Eq)
 
-- Skapar en profil med namnet n och en lista av MolSeq mols
-- Varken n eller mols får vara tomma listor och mols måste vara av samma typ och dimension (kollas i validatePro)
molseqs2profile :: [Char] -> [MolSeq] -> Profile
molseqs2profile n mols
        |(n == []) = error "molseqs2profile: Name is empty"
        |(mols == []) || ((validatePro mols) == False) = error "Error: molseqs2profile"
        |otherwise = Profile 
                    { proName = n
                    , proMatrix = makeProfileMatrix mols
                    , proAmount = fromIntegral(length mols) -- proAmount är antalet sekvenser i profilen (längden av den yttre listan)
                    , proType = getProType (validatePro mols) (head mols)}
 
-- Returnerar typen av en MolSeq i listan, givet att alla molSeqs är av samma typ och längd
getProType :: Bool -> MolSeq -> Type
getProType b m 
        |b == True = checkType(seqSequence m)
        |otherwise = error "Error: Sequences in profile must be of same type."
 
-- Går igenom hela listan och kollar att samtliga MolSeq är av samma typ och längd
validatePro :: [MolSeq] -> Bool
validatePro (x:xs) 
        |(length xs == 0) = True
        |(checkType(seqSequence x) == checkType(seqSequence(head xs))) && (length(seqSequence x) == length(seqSequence (head xs))) = validatePro xs
        |otherwise = False
 
nucleotides = "ACGT"
aminoacids = sort "ARNDCEQGHILKMFPSTWYVX"
 
-- Tar in en lista med MolSeq och returnerar en matris på profilen
makeProfileMatrix :: [MolSeq] -> [[(Char, Int)]]
makeProfileMatrix [] = error "Empty sequence list"
makeProfileMatrix sl = res
  where 
    -- om t är DNA, set default för nucletides annars aminoacids.
    t = molType (head sl)
     -- skapa tuples för varje element, där det snd är 0      
    defaults = 
      if (t == DNA) then
        -- skapar en lista med tuples såhär [(A, 0), (C, 0), (G, 0), (T, 0)]
        zip nucleotides (replicate (length nucleotides) 0) -- Rad (i)
      else 
        -- skapar en lista med tuples såhär [(A, 0), (R, 0), ... (X, 0)]
        zip aminoacids (replicate (length aminoacids) 0)   -- Rad (ii)
    -- skapar en lista via map med alla sekvenser i sl
    strs = map seqSequence sl                              -- Rad (iii)
    -- skapar en lista av en lista som innehåller tuples
    -- varje element är en lista med tuples
    -- tuplesen indikerar (char, hur många gånger denna char har hittats i denna MolSeq sekvensen)
    tmp1 = map (map (\x -> ((head x), (length x))) . group . sort)
               (transpose strs)                            -- Rad (iv)
    -- kolla om bokstaven är likadana, returenar isåfall True
    equalFst a b = (fst a) == (fst b)
    -- returnerar en matris (typles i listor i lista) som är sorterade i bokstavsordning 
    res = map sort (map (\l -> unionBy equalFst l defaults) tmp1)
 
 
profileName :: Profile -> [Char]
profileName n = proName n
 
---------------------------------------------------------
 
 -- Givet en profil och en plats i dess matris så returnerar metoden en relativ frekvens av en bokstav i profilen
profileFrequency :: Profile -> Int -> Char -> Double
profileFrequency pro i j 
        |(i < length(proMatrix pro)) = ((pfHelp (proMatrix pro) i (toUpper j)) / (proAmount pro))
        |otherwise = error "Error: profileFrequency"
 
 -- Går igenom den (yttre listan) för att hitta den angivna int platsen
pfHelp :: [[(Char, Int)]] -> Int -> Char -> Double
pfHelp (x:xs) i j 
        |(i == 0) = pfHelp2 x j
        |(i > 0) = pfHelp xs (i-1) j
        |otherwise = error "Error: pfHelp"
 
-- Utifrån den givna int platsen går denna funktion igenom char värderna tills den hittar rätt plats
pfHelp2 :: [(Char, Int)] -> Char -> Double
pfHelp2 y j
        | (fst (head y) /= j) = (pfHelp2 (tail y) j)
        | (fst (head y) == j) = (fromIntegral (snd (head y)))
        | otherwise = error "Error: pfHelp2"
 
---------------------------------------------------------
 
-- Tar in två profiler och mäter distansen mellan deras matriser
-- Funktionen summerar skillnaden mellan samtliga tuples och returnerar det relativa avståndet
profileDistance :: Profile -> Profile -> Double
profileDistance p1 p2 = matrixSum (proMatrix p1) (proMatrix p2)
    where 
    matrixSum :: [[(Char, Int)]] -> [[(Char, Int)]] -> Double
    matrixSum [] [] = 0.0
    matrixSum (x:xs) (y:ys) = (listSum x y) + (matrixSum xs ys)
        where
        listSum :: [(Char, Int)] -> [(Char, Int)] -> Double
        listSum [] [] = 0.0
        listSum (x:xs) (y:ys) = (cellSum x y) + (listSum xs ys)
            where
            cellSum :: (Char, Int) -> (Char, Int) -> Double
            cellSum (_, i1) (_, i2) = abs((division i1 length1) - (division i2 length2))
                where
                division :: Int -> Double -> Double 
                division a b = (fromIntegral a) / b
                length1 = proAmount p1
                length2 = proAmount p2
 
 
-------------------------------------------------------
 
class Evol a where
        name :: a -> [Char]
        distance :: a -> a -> Double
        typeE :: a -> Type 
        distanceMatrix :: [a] -> [([Char], [Char], Double)]
 
 
instance Evol MolSeq where
        name a = molName a
        distance a b = seqDistance a b
        typeE a = molType a
        distanceMatrix a = distMatrix a a 
 
instance Evol Profile where
        name a = proName a 
        distance a b = profileDistance a b
        typeE a = proType a
        distanceMatrix a = distMatrix a a 
 
-------------------------------------------------------
 
-- Tar in två stycken matriser/listor och returnerar en distMatrix med distansen mellan båda profilerna på alla platser
-- distMatrix returnerar alltså en lista med namnen och distansen mellan dem
distMatrix :: (Eq a, Evol a) => [a] -> [a] ->[([Char], [Char], Double)]
distMatrix [] _ = []
distMatrix a (x:xs) 
        |length xs == 0 = ((name (head a)), (name x), (distance (head a) x)):distMatrix (tail a) (tail a) 
        |otherwise = (name (head a), (name x), (distance (head a) x)):distMatrix a xs 
 